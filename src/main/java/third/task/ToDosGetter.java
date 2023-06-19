package third.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDosGetter
{
    HttpClient client = HttpClient.newHttpClient();

    public void createJsonWithAllOpenToDosByUserId(int userId) throws IOException, InterruptedException
    {
        String allTodosJson = getAllTodosByUserId(userId);
        List<ToDosJPH> allTodos = getOpenTodosFromJson(allTodosJson);
        String jsonFilePath = "src/main/resources/" + "user-" + userId + "-open_todos.json";
        createJsonWithTodos(allTodos, jsonFilePath);
        System.out.println("JSON filepath: " + jsonFilePath);
    }

    private String getAllTodosByUserId(int userId) throws IOException, InterruptedException
    {
        String uri = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private List<ToDosJPH> getOpenTodosFromJson(String json)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ToDosJPH[] todosArray = gson.fromJson(json, ToDosJPH[].class);
        List<ToDosJPH> todosList = new ArrayList<>(Arrays.asList(todosArray));
        List<ToDosJPH> openTodosList = new ArrayList<>();
        for (ToDosJPH element : todosList)
        {
            if (!element.isCompleted())
            {
                openTodosList.add(element);
            }
        }
        return openTodosList;
    }

    private void createJsonWithTodos(List<ToDosJPH> todos, String jsonFilePath)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String outputString = gson.toJson(todos.toArray());
        try (FileWriter output = new FileWriter(jsonFilePath))
        {
            output.write(outputString);
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }

    }
}
