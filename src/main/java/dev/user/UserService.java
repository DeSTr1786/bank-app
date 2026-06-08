package dev.user;

import dev.account.AccountService;

import java.util.*;

public class UserService {
    private int idCounter;

    private final Map<Integer, User> userMap;
    private final Set<String> takenLogins;
    private final AccountService accountService;

    public UserService(AccountService accountService) {
        this.accountService = accountService;
        this.userMap = new HashMap<>();
        this.idCounter = 0;
        this.takenLogins = new HashSet<>();
    }

    public User createUser(String login) {

        if (takenLogins.contains(login)) {
            throw new IllegalArgumentException("Пользователь с логином =%s уже существует."
                    .formatted(login));
        }
        takenLogins.add(login);
        idCounter++;
        User newUser = new User(idCounter, login, new ArrayList<>());
        var newAccount = AccountService.createAccount(newUser);
        newUser.getAccountList().add(newAccount);
        userMap.put(newUser.getId(), newUser);
        return newUser;
    }

    public Optional<User> findUserById (int id) {
        return Optional.ofNullable(userMap.get(id));

    }

    public List<User> getAllUsers() {
        return userMap.values().stream().toList();
    }
}
