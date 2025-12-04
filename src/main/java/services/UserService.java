package services;

import dao.UserDAO;
import entities.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Hasher le mot de passe (SHA-256)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hashage du mot de passe");
        }
    }

    public void creerUtilisateur(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty())
            throw new RuntimeException("Le nom d'utilisateur est obligatoire.");

        if (user.getPassword() == null || user.getPassword().length() < 6)
            throw new RuntimeException("Le mot de passe doit contenir au moins 6 caractères.");

        User existant = userDAO.findByUsername(user.getUsername());
        if (existant != null)
            throw new RuntimeException("Ce nom d'utilisateur existe déjà.");

        user.setPassword(hashPassword(user.getPassword()));
        userDAO.add(user);
    }
    public User authentifier(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException("Nom d'utilisateur et mot de passe requis.");

        String hashedPassword = hashPassword(password);
        User user = userDAO.authenticate(username, hashedPassword);

        if (user == null)
            throw new RuntimeException("Identifiants incorrects.");

        return user;
    }

    public void modifier(User user) {
        userDAO.update(user);
    }

    public void supprimer(int id) {
        userDAO.delete(id);
    }

    public List<User> lister() {
        return userDAO.getAll();
    }

    public User chercher(int id) {
        return userDAO.getById(id);
    }

    public User chercherParUsername(String username) {
        return userDAO.findByUsername(username);
    }
}