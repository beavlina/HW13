package first.task;

public class UserCrudAppTests
{
    public static void main(String[] args)
    {
        UserCrudApp userCrudApp = new UserCrudApp();
        //   System.out.println(userCrudApp.createUser());
        //   System.out.println(userCrudApp.updateUser(1));
        //   System.out.println(userCrudApp.deleteUser(2));
        //   System.out.println(userCrudApp.getAllUsers());
        //   System.out.println(userCrudApp.getUserById(6));
        System.out.println(userCrudApp.getUserByUsername(("Karianne")));
    }
}