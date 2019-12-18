package com.example.demo;

import com.example.demo.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Product> productos = template.query("DESCRIBE BASE;", new RowMapper<Product>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Product(rs.getInt("nro_medidor"));
            }
        });
        for (int i=0;i<10;i++){
            System.out.println(productos.get(i).getNro_medidor());;
        }

    }
}
