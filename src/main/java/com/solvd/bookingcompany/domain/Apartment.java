package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.enums.ApartmentType;
import com.solvd.bookingcompany.interfaces.Searchable;
import com.solvd.bookingcompany.search.SearchCriteria;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Apartment extends BaseEntity implements Searchable {

    private String title;
    private Address address;
    private Host host;
    private Double basePricePerNight;
    private Integer rooms;
    private Integer maxGuests;
    private List<Availability> availabilities = new ArrayList<>();
    private List<Photo> photos = new ArrayList<>();
    private Queue<Customer> waitingList = new LinkedList<>();
    private ApartmentType type;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(Apartment.class);

    public Apartment() {
    }

    public Apartment(Long id, String title, Address address, Host host,
                     Double basePricePerNight, Integer rooms, Integer maxGuests,
                     List<Availability> availabilities, List<Photo> photos,
                     Queue<Customer> waitingList, ApartmentType type) {
        super(id);
        this.title = title;
        this.address = address;
        this.host = host;
        this.basePricePerNight = basePricePerNight;
        this.rooms = rooms;
        this.maxGuests = maxGuests;
        this.availabilities = availabilities;
        this.photos = photos;
        this.waitingList = waitingList;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Double getBasePricePerNight() {
        return basePricePerNight;
    }

    public void setBasePricePerNight(Double basePricePerNight) {
        this.basePricePerNight = basePricePerNight;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Queue<Customer> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Queue<Customer> waitingList) {
        this.waitingList = waitingList;
    }

    public ApartmentType getType() {
        return type;
    }

    public void setType(ApartmentType type) {
        this.type = type;
    }

    @Override
    public String getEntityName() {
        return "Apartment";
    }

    @Override
    public boolean matches(SearchCriteria criteria) {
        for (Availability a : availabilities) {
            if (a.matches(criteria)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAvailable() {
        if (availabilities == null || availabilities.isEmpty()) {
            return true;
        }

        for (Availability a : availabilities) {
            if (a.isAvailable()) {
                return false;
            }
        }
        return true;
    }
}
