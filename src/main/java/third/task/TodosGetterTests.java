package third.task;

import java.io.IOException;

public class TodosGetterTests
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ToDosGetter src = new ToDosGetter();
        int userId = 1;
        src.createJsonWithAllOpenToDosByUserId(userId);
    }
}
