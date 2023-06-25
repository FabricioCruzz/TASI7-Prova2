package br.com.si7.prova1.fabricioadrianodacruz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.si7.prova1.fabricioadrianodacruz.entities.ProductEntity;
import br.com.si7.prova1.fabricioadrianodacruz.repository.ProductRepository;

@SpringBootApplication
public class FabricioAdrianoDaCruzApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(FabricioAdrianoDaCruzApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		ProductEntity p1 = new ProductEntity((long) 1, "Macarrão", "Tipo Espaguete", 5.99f, "Massas", true);
		repo.save(p1);
		
		ProductEntity p2 = new ProductEntity((long) 2, "Detergente", "Ypê", 4.25f, "Limpeza", true);
		repo.save(p2);
		
	}

}
