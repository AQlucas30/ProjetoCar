package carros.Repository;



import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository <carros.Model.Car, Long> {
	
}
