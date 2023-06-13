import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.AviaSouls;
import ru.netology.Ticket;
import ru.netology.TicketTimeComparator;

import java.util.Arrays;

public class AviaSoulTest {
    AviaSouls aviaSouls = new AviaSouls();
    TicketTimeComparator timeComparator = new TicketTimeComparator();

    Ticket ticket1 = new Ticket("Москва", "Сочи", 1200, 12, 19);
    Ticket ticket2 = new Ticket("Москва", "Сочи", 1100, 11, 16);
    Ticket ticket3 = new Ticket("Москва", "Сочи", 1300, 11, 13);
    Ticket ticket4 = new Ticket("Москва", "Питер", 1100, 15, 19);
    Ticket ticket5 = new Ticket("Москва", "Питер", 2000, 11, 13);


    @BeforeEach
    public void setUp() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
    }

    @Test
    public void shouldSortIfLess() {
        Assertions.assertEquals(-1, ticket3.compareTo(ticket5));
        Assertions.assertEquals(-1, ticket1.compareTo(ticket3));
    }

    @Test
    public void shouldSortIfHigh() {
        Assertions.assertEquals(1, ticket1.compareTo(ticket2));
        Assertions.assertEquals(1, ticket3.compareTo(ticket4));
    }

    @Test
    public void shouldSortIfEqual() {
        Assertions.assertEquals(0, ticket2.compareTo(ticket4));
    }

    @Test
    public void shouldSearchAnsSortOfPrice() {
        Ticket[] expected = {ticket2, ticket1, ticket3};
        Ticket[] actual = aviaSouls.search("Москва", "Сочи");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldCompareOfFlightTimeHigh() {
        Assertions.assertEquals(1, timeComparator.compare(ticket1, ticket2));
        Assertions.assertEquals(1, timeComparator.compare(ticket2, ticket3));
    }

    @Test
    public void shouldCompareOfFlightTimeLess() {
        Assertions.assertEquals(-1, timeComparator.compare(ticket5, ticket1));
        Assertions.assertEquals(-1, timeComparator.compare(ticket4, ticket2));
    }

    @Test
    public void shouldCompareOfFlightTimeEqual() {
        Assertions.assertEquals(0, timeComparator.compare(ticket5, ticket3));
    }

    @Test
    public void shouldSearchAndSort() {
        Ticket[] tickets = aviaSouls.findAll();
        Arrays.sort(tickets, timeComparator);

        Ticket[] expected = {ticket3, ticket2, ticket1};
        Ticket[] actual = aviaSouls.searchAndSortBy("Москва", "Сочи", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}

