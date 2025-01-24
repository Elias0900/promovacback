package com.tresorerie.voyage.dto;

import com.tresorerie.voyage.model.MyAppUser;
import com.tresorerie.voyage.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyAppUserDto {

    private Long id;
    private String username;
    private String email;
    private RoleType roleType; // Enum pour le rôle (EMPLOYE ou DIRECTEUR)
    private List<VenteDto> transactions; // Liste simplifiée des transactions
    private BilanDto bilan; // Objet simplifié pour le bilan

    /**
     * Convertit une entité `MyAppUser` en un DTO `MyAppUserDto`.
     *
     * @param user L'entité source.
     * @return Un DTO `MyAppUserDto`.
     */
    public static MyAppUserDto fromEntity(MyAppUser user) {
        if (user == null) {
            return null;
        }

        return MyAppUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roleType(user.getRoleType())
                .transactions(user.getTransaction() != null
                        ? user.getTransaction().stream()
                        .map(VenteDto::fromEntity) // Mapper de VenteDto
                        .collect(Collectors.toList())
                        : null)
                .bilan(user.getBilan() != null
                        ? BilanDto.fromEntity(user.getBilan()) // Mapper de BilanDto
                        : null)
                .build();
    }

    /**
     * Convertit un DTO `MyAppUserDto` en une entité `MyAppUser`.
     *
     * @param userDto Le DTO source.
     * @return Une entité `MyAppUser`.
     */
    public static MyAppUser toEntity(MyAppUserDto userDto) {
        if (userDto == null) {
            return null;
        }

        MyAppUser user = MyAppUser.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .roleType(userDto.getRoleType())
                .build();

        if (userDto.getTransactions() != null) {
            user.setTransaction(userDto.getTransactions().stream()
                    .map(VenteDto::toEntity) // Mapper de VenteDto
                    .collect(Collectors.toList()));
        }

        if (userDto.getBilan() != null) {
            user.setBilan(BilanDto.toEntity(userDto.getBilan())); // Mapper de BilanDto
        }

        return user;
    }
}
