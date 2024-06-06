package ru.itis.healthserviceimpl.model.role;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.healthserviceimpl.model.AbstractModel;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.CommunityRoleType;

import java.util.List;

@Entity
@Table(name = "community_role_type")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommunityRole extends AbstractModel {
    @Column(name = "type")
    private CommunityRoleType type;

    @OneToMany(mappedBy = "role")
    private List<User> user;
}
