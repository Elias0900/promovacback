ackage com.tresorerie.voyage.Auth;

import com.tresorerie.voyage.entity.User;
import com.tresorerie.voyage.utils.HashUtil;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
@Service
public class AuthSystem {
    // Stocke les utilisateurs par leur identifiant
    private final Map<String, User> utilisateurs = new HashMap<>();

    // Crée un nouvel utilisateur avec mot de passe haché
    public void creerUtilisateur(String identifiant, String motDePasse, String role) throws NoSuchAlgorithmException {
        String motDePasseHache = HashUtil.hacherMotDePasse(motDePasse);
        User utilisateur = new User();
        utilisateur.setUsername(identifiant);
        utilisateur.setPassword(motDePasseHache); // Assurez-vous que vous définissez le mot de passe haché
        utilisateur.setRole(role);
        utilisateurs.put(identifiant, utilisateur);
    }

    // Vérifie si l'identifiant et le mot de passe correspondent
    public boolean authentifier(String identifiant, String motDePasse) throws NoSuchAlgorithmException {
        User utilisateur = utilisateurs.get(identifiant);
        if (utilisateur != null) {
            String motDePasseHache = HashUtil.hacherMotDePasse(motDePasse); // Hachage du mot de passe entré
            return utilisateur.getPassword().equals(motDePasseHache); // Comparaison avec le mot de passe haché stocké
        }
        return false;
    }
}
