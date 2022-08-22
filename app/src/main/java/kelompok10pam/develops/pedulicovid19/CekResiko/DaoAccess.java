package kelompok10pam.develops.pedulicovid19.CekResiko;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;



import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    long insertTodo(Cekresiko todo);

    @Insert
    void insertTodoList(List<Cekresiko> todoList);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO)
    List<Cekresiko> fetchAllTodos();

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE category = :category")
    List<Cekresiko> fetchTodoListByCategory(String category);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE todo_id = :todoId")
    Cekresiko fetchTodoListById(int todoId);

    @Update
    int updateTodo(Cekresiko todo);

    @Delete
    int deleteTodo(Cekresiko todo);
}
