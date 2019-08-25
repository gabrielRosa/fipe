package com.dbc.prova.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "fipe")
public class User implements Serializable {

    private static final long serialVersionUID = -2198804831975108899L;

    @Id
    private ObjectId _id;

    @Indexed(unique = true)
    private String email;
    private String password;
    private List<String> userHistory = new ArrayList<>();

}
