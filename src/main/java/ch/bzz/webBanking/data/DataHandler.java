package ch.bzz.webBanking.data;

import ch.bzz.webBanking.model.Client;
import ch.bzz.webBanking.model.Account;
import ch.bzz.webBanking.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 * M133: Banking
 *
 * @author Leon Funtik
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Client> clientMap;
    private static Map<String, Account> accountMap;


    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        clientMap = new HashMap<>();
        accountMap = new HashMap<>();
        readJSON();
    }

    public static Map<String, Client> readClients() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get(Config.getProperty("clientJSON")));
            clientMap = gson.fromJson(reader, Map.class);
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientMap;
    }

    public static Map<String, Account> readAccounts() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get(Config.getProperty("clientJSON")));
            accountMap = gson.fromJson(reader, Map.class);
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountMap;
    }

    /**
     * reads a single client identified by its uuid
     * @param clientUUID  the identifier
     * @return client-object
     */
    public static Client readClient(String clientUUID) {
        Client client = new Client();
        if (getClientMap().containsKey(clientUUID)) {
            client = getClientMap().get(clientUUID);
        }
        return client;
    }

    /**
     * reads a single account identified by its uuid
     * @param accountUUID  the identifier
     * @return account-object
     */
    public static Account readAccount(String accountUUID) {
        Account account = new Account();
        if (getAccountMap().containsKey(accountUUID)) {
            account = getAccountMap().get(accountUUID);
        }
        return account;
    }



    /**
     * saves a client
     * @param client  the client to be saved
     */
    public static void saveClient(Client client) {
        getClientMap().put(client.getClientUUID(), client);
        writeJSON();
    }





    /**
     * saves a account
     * @param account  the account to be saved
     */
    public static void saveAccount(Account account) {
        getAccountMap().put(account.getAccountUUID(), account);
        writeJSON();
    }

    /**
     * gets the clientMap
     * @return the clientMap
     */
    public static Map<String, Client> getClientMap() {
        return clientMap;
    }

    /**
     * gets the getAccountMap
     * @return the getAccountMap
     */
    public static Map<String, Account> getAccountMap() {
        return accountMap;
    }

    public static void setAccountMap(Map<String, Account> accountMap) {
        DataHandler.accountMap = accountMap;
    }

    /**
     * reads the Clients and Accounts
     */
    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("clientJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Client[] clients = objectMapper.readValue(jsonData, Client[].class);
            for (Client client : clients) {
                String accountUUID = client.getAccount().getAccountUUID();
                Account account;
                if (getAccountMap().containsKey(accountUUID)) {
                    account = getAccountMap().get(accountUUID);
                } else {
                    account = client.getAccount();
                    getAccountMap().put(accountUUID, account);
                }
                client.setAccount(account);
                getClientMap().put(client.getClientUUID(), client);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write the clients and accounts
     *
     */
    private static void writeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        Writer writer;
        FileOutputStream fileOutputStream = null;

        String clientPath = Config.getProperty("clientJSON");
        try {
            fileOutputStream = new FileOutputStream(clientPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectMapper.writeValue(writer, getClientMap().values());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * public static void saveClients(Client client) {
     *         try {
     *             Gson gson = new Gson();
     *             Writer writer = new BufferedWriter(new FileWriter(String.valueOf(Paths.get(Config.getProperty("listeJSON")))));
     *
     *             gson.toJson(listen, writer);
     *             writer.close();
     *
     *         } catch (Exception e) {
     *             e.printStackTrace();
     *         }
     *     }
     *
     */

}