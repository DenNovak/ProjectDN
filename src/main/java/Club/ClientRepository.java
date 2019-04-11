package Club;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query("SELECT COUNT(n) FROM Client n")
    Integer countClients();

}