import { Component } from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  searchQuery = '';
  profiles: any[] = [];
  darkMode = false;

  async searchProfiles(event: Event) {
    event.preventDefault();
    if (!this.searchQuery.trim()) return;

    try {
      const { data } = await axios.get(`https://api.github.com/search/users?q=${this.searchQuery}`);
      const profilesData = await Promise.all(
        data.items.map(async (user: any) => {
          const userData = await axios.get(user.url);
          const repos = await axios.get(userData.data.repos_url);

          // Top repositories by stars
          const topStarredRepos = repos.data
            .sort((a: any, b: any) => b.stargazers_count - a.stargazers_count)
            .slice(0, 3);

          // Top repositories by forks
          const topForkedRepos = repos.data
            .sort((a: any, b: any) => b.forks_count - a.forks_count)
            .slice(0, 3);

          // Language stats
          const languageStats: any = {};
          await Promise.all(
            repos.data.map(async (repo: any) => {
              const languages = await axios.get(repo.languages_url);
              for (const [lang, bytes] of Object.entries(languages.data)) {
                languageStats[lang] = (languageStats[lang] || 0) + bytes;
              }
            })
          );

          const sortedLangs = Object.entries(languageStats).sort((a: any, b: any) => b[1] - a[1]);
          const langHtml = sortedLangs.map(([lang, bytes]) => {
            // Ensure bytes is a number before performing math operations
            const sizeInKB = (typeof bytes === 'number') ? Math.round(bytes / 1024) : 'Unknown';
            return `<li>${lang} (${sizeInKB} KB)</li>`;
          }).join('');

          return {
            login: user.login,
            name: userData.data.name || 'N/A',
            location: userData.data.location || 'N/A',
            followers: userData.data.followers,
            following: userData.data.following,
            public_repos: userData.data.public_repos,
            public_gists: userData.data.public_gists,
            blog: userData.data.blog || 'N/A',
            updated_at: userData.data.updated_at,
            html_url: user.html_url,
            avatar_url: userData.data.avatar_url,
            topStarredRepos,
            topForkedRepos,
            languageStats: langHtml,
          };
        })
      );
      this.profiles = profilesData;
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }

  toggleDarkMode() {
    this.darkMode = !this.darkMode;
  }

  exportData() {
    const header = [
      'Login', 'Name', 'Location', 'Followers', 'Following', 'Public Repos', 'Public Gists',
      'Website', 'Last Updated', 'Profile URL', 'Top Starred Repositories', 'Top Forked Repositories', 'Language Stats'
    ];
    const rows = this.profiles.map(profile => [
      profile.login,
      profile.name,
      profile.location,
      profile.followers,
      profile.following,
      profile.public_repos,
      profile.public_gists,
      profile.blog,
      profile.updated_at,
      profile.html_url,
      profile.topStarredRepos.map((repo: any) => `${repo.name} (${repo.stargazers_count} stars)`).join(' | '),
      profile.topForkedRepos.map((repo: any) => `${repo.name} (${repo.forks_count} forks)`).join(' | '),
      profile.languageStats.replace(/<\/?li>/g, '')
    ]);
    const csvContent = [
      header.join(','),
      ...rows.map(row => row.join(','))
    ].join('\n');
    
    const encodedUri = encodeURI(`data:text/csv;charset=utf-8,${csvContent}`);
    const link = document.createElement('a');
    link.setAttribute('href', encodedUri);
    link.setAttribute('download', 'github_profiles.csv');
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }
}
