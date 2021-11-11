package co.edu.usa.Reto3;

import co.edu.usa.Reto3.models.Category;
import co.edu.usa.Reto3.models.Client;
import co.edu.usa.Reto3.repositories.CategoryRepository;
import co.edu.usa.Reto3.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.usa.Reto3"})
public class Reto3Application {

    @Autowired
    private CategoryRepository categorias;

    @Autowired
    private ClientRepository clientes;

    public static void main(String[] args) {
        SpringApplication.run(Reto3Application.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            List<Category> ps = categorias.getAll();
            System.out.println("Categorias: " + ps.size());

            List<Client> cs = clientes.getAll();
            System.out.println("Clientes: " + cs.size());
        };
    }

}
