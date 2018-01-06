package be.kokw.repositories.books;

import be.kokw.bean.books.Derated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("derateRepo")
public interface DerateRepo extends JpaRepository<Derated,Integer> {
}
