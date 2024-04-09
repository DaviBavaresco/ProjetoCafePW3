package br.edu.ifrs.projetocafe.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.ifrs.projetocafe.entity.Cafe;
@Dao
public interface CafeDAO {

    @Query("SELECT * FROM Cafe")
    public List<Cafe> getAllCafes();

    @Query("SELECT * FROM Cafe WHERE nome = :name")
    public List<Cafe> getCafeByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Cafe cafe);

    @Update
    public void update(Cafe cafe);

    @Delete
    public void delete(Cafe cafe);
}
