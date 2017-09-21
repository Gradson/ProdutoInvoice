# ProdutoInvoice

Projeto desenvolvido para gerenciar mercadorias(produtos) e notas fiscais. 

# Módulos

Este sistema possui dois módulos:
- Backend:

   Api para gerenciamento dos produtos e nota fiscais.
   
   -- Tecnologia principais:
   
      Java 1.8
      Spring Boot 1.5.7
      Maven 3.3.9
      JPA
      HSQLDB
      JMS
      ActiveMQ
      JUnit
      PostgreSQL
      Swagger
      
  -- Execução
  
      $ mvn spring-boot:run
  
  Após o start o projeto estará disponível em: 
  
       http://localhost:8080/atech
  
  toda a documentação da api estará disponível em:
  
       http://localhost:8080/atech/swagger-ui.html
       
     Todos os testes podem ser executados utilizando:
     
        $ mvn test
  
- UI:

  Interface para que o usuário possa cadastrar e consultar mercadorias e notas fiscais.
  
  -- Tecnologia Principais:
  
      AngularJS
      Babel
      Bower
      Gulp
      ES6
      
  -- Execução
  
      gulp serve
      
   Após o start, o projeto estará disponível em:
          
          http://localhost:3000
