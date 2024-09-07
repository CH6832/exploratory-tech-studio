namespace DocumentManagementSystem
{
    public class AuthenticationService
    {
        private readonly UserManager userManager;

        public AuthenticationService()
        {
            this.userManager = new UserManager();
        }

        public bool Authenticate(string username, string password)
        {
            User user = userManager.GetUserByUsername(username);
            return user != null && user.Password == password;
        }
    }

    // Placeholder class for UserManager
    class UserManager
    {
        public User GetUserByUsername(string username)
        {
            // Implement logic to retrieve user by username here
            return new User(username, "password"); // Placeholder
        }
    }

    // Placeholder class for User
    class User
    {
        public string Username { get; }
        public string Password { get; }

        public User(string username, string password)
        {
            Username = username;
            Password = password;
        }
    }
}
