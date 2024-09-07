using System.Collections.Generic;
using System.Linq;

namespace DocumentManagementSystem
{
    public class UserManager
    {
        private readonly List<User> users;

        public UserManager()
        {
            users = new List<User>
            {
                new User("admin", "1234") // Default user
            };
        }

        public User GetUserByUsername(string username)
        {
            return users.FirstOrDefault(user => user.Username == username);
        }
    }
}
