import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

const App = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [profiles, setProfiles] = useState([]);
  const [darkMode, setDarkMode] = useState(false);

  const searchProfiles = async (e) => {
    e.preventDefault();
    if (!searchQuery.trim()) return;

    try {
      const { data } = await axios.get(`https://api.github.com/search/users?q=${searchQuery}`);
      const profilesData = await Promise.all(
        data.items.map(async (user) => {
          const userData = await axios.get(user.url);
          const repos = await axios.get(userData.data.repos_url);

          // Top repositories by stars
          const topStarredRepos = repos.data
            .sort((a, b) => b.stargazers_count - a.stargazers_count)
            .slice(0, 3);

          // Top repositories by forks
          const topForkedRepos = repos.data
            .sort((a, b) => b.forks_count - a.forks_count)
            .slice(0, 3);

          // Language stats
          const languageStats = {};
          await Promise.all(
            repos.data.map(async (repo) => {
              const languages = await axios.get(repo.languages_url);
              for (const [lang, bytes] of Object.entries(languages.data)) {
                languageStats[lang] = (languageStats[lang] || 0) + bytes;
              }
            })
          );

          const sortedLangs = Object.entries(languageStats).sort((a, b) => b[1] - a[1]);
          const langHtml = sortedLangs.map(([lang, bytes]) => `<li>${lang} (${Math.round(bytes / 1024)} KB)</li>`).join('');

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
            topStarredRepos,
            topForkedRepos,
            languageStats: langHtml,
          };
        })
      );
      setProfiles(profilesData);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
  };

  const exportData = () => {
    const header = [
      'Login', 'Name', 'Location', 'Followers', 'Following', 'Public Repos', 'Public Gists',
      'Website', 'Last Updated', 'Profile URL', 'Top Starred Repositories', 'Top Forked Repositories', 'Language Stats'
    ];
    const rows = profiles.map(profile => [
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
      profile.topStarredRepos.map(repo => `${repo.name} (${repo.stargazers_count} stars)`).join(' | '),
      profile.topForkedRepos.map(repo => `${repo.name} (${repo.forks_count} forks)`).join(' | '),
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
  };

  return (
    <div className={`container ${darkMode ? 'dark-mode' : ''}`}>
      <form onSubmit={searchProfiles}>
        <div className="form-group">
          <label>Search Github Users:</label>
          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="form-control"
          />
        </div>
        <button type="submit" className="btn btn-primary">Search</button>
        <button type="button" onClick={toggleDarkMode} className="btn btn-dark">Toggle Dark Mode</button>
        <button type="button" onClick={exportData} className="btn btn-success">Export Data</button>
      </form>
      <div id="profiles">
        {profiles.map((profile, index) => (
          <div className="panel panel-default profile-container" key={index}>
            <div className="panel-heading">
              <h3 className="panel-title">{profile.login}</h3>
            </div>
            <div className="panel-body">
              <div className="row">
                <div className="col-md-3">
                  <img src={profile.avatar_url} alt={profile.login} />
                </div>
                <div className="col-md-9">
                  <p><strong>Name:</strong> {profile.name}</p>
                  <p><strong>Location:</strong> {profile.location}</p>
                  <p><strong>Followers:</strong> {profile.followers}</p>
                  <p><strong>Following:</strong> {profile.following}</p>
                  <p><strong>Public Repos:</strong> {profile.public_repos}</p>
                  <p><strong>Public Gists:</strong> {profile.public_gists}</p>
                  <p><strong>Top Starred Repositories:</strong></p>
                  <ul>
                    {profile.topStarredRepos.map(repo => (
                      <li key={repo.id}><a href={repo.html_url} target="_blank" rel="noopener noreferrer">{repo.name}</a> ({repo.stargazers_count} stars)</li>
                    ))}
                  </ul>
                  <p><strong>Top Forked Repositories:</strong></p>
                  <ul>
                    {profile.topForkedRepos.map(repo => (
                      <li key={repo.id}><a href={repo.html_url} target="_blank" rel="noopener noreferrer">{repo.name}</a> ({repo.forks_count} forks)</li>
                    ))}
                  </ul>
                  <p><strong>Language Stats:</strong></p>
                  <ul dangerouslySetInnerHTML={{ __html: profile.languageStats }} />
                  <p><strong>Recent Activity:</strong> {profile.updated_at}</p>
                  <br />
                  <a className="btn btn-default" href={profile.html_url} target="_blank" rel="noopener noreferrer">Visit Github</a>
                  {profile.blog ? <a className="btn btn-default" href={profile.blog} target="_blank" rel="noopener noreferrer">Visit Website</a> : ''}
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default App;
