package com.bakery.test.entities;

import com.bakery.test.models.dtos.CakeDto;
import com.bakery.test.models.enums.StatusType;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.persistence.*;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "cake")
public class Cake implements Serializable {

//    @Autowired
//    ModelMapper modelMapper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String cakeTitle;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "valid_until")
    private Date validUntilDate;

    public Cake() {
    }

    public Cake(String cakeTitle, Date releaseDate, Date validUntilDate) {
        this.cakeTitle = cakeTitle;
        this.releaseDate = releaseDate;
        this.validUntilDate = validUntilDate;
    }

    public StatusType defineStatus() {
        GregorianCalendar calendar = new GregorianCalendar();
        if (validUntilDate.before(calendar.getTime())) {
            return StatusType.STALE;
        }
        return StatusType.FRESH;
    }

//    public CakeDto toDto() {
//        CakeDto cakeDto = new CakeDto();
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.addMappings(new PropertyMap<Cake, CakeDto>() {
//            @Override
//            protected void configure() {
//                map().setStatus(source.defineStatus());
//            }
//        });
//        modelMapper.map(this, cakeDto);
//        return cakeDto;
//    }

    public int getId() {
        return id;
    }

    public String getCakeTitle() {
        return cakeTitle;
    }

    public void setCakeTitle(String cakeTitle) {
        this.cakeTitle = cakeTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(Date validUntilDate) {
        this.validUntilDate = validUntilDate;
    }
}
