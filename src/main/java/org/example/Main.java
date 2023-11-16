package org.example;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.service.ClientService;
import org.example.service.PlanetService;
import org.example.service.TicketService;
import org.example.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        clientServiceTest();
        planetServiceTest();
        ticketServiceTest();

        HibernateUtil.close();
    }

    private static void clientServiceTest() {
        ClientService clientService = new ClientService();

        // Create new client
        Client client = clientService.create("Froot");
        System.out.println("Saved client = " + client);

        // Update client
        client.setName("Groot");
        clientService.update(client);

        // Getting client by id
        Client clientFromDB = clientService.getById(client.getId());
        System.out.println("Client after update = " + clientFromDB);

        // Delete client
        clientService.delete(clientFromDB);

        // Find all clients from DB
        System.out.println("==========================");
        clientService.findAll().forEach(System.out::println);
        System.out.println("==========================");
    }

    private static void planetServiceTest() {
        PlanetService planetService = new PlanetService();

        // Create new planet
        Planet planet = planetService.create("EARTH15", "Earth");
        System.out.println("Saved planet = " + planet);

        // Update planet
        planet.setName("Earth 15");
        planetService.update(planet);

        // Getting planet by id
        Planet planetFromDB = planetService.getById(planet.getId());
        System.out.println("Planet after update = " + planetFromDB);

        // Delete planet
        planetService.deleteById(planetFromDB.getId());

        // Find all planets from DB
        System.out.println("==========================");
        planetService.findAll().forEach(System.out::println);
        System.out.println("==========================");
    }

    private static void ticketServiceTest() {
        ClientService clientService = new ClientService();
        PlanetService planetService = new PlanetService();
        TicketService ticketService = new TicketService();

        Client client = clientService.create("Rocket");
        Planet earth312 = planetService.create("EARTH312", "Earth 312");
        Planet mars333 = planetService.create("MARS333", "Mars 333");
        Ticket ticket = ticketService.create(client, earth312, mars333);
        System.out.println("Saved ticket = " + ticket);

        Ticket ticketFromDB = ticketService.findById(ticket.getId());
        System.out.println("Get ticket by id => " + ticketFromDB);

        ticketService.delete(ticketFromDB);
        clientService.delete(client);
        planetService.delete(earth312);
        planetService.delete(mars333);

        System.out.println("=========================");
        ticketService.findAll().forEach(System.out::println);
        System.out.println("=========================");
    }
}