package rmiclient;

import java.rmi.*;
import java.rmi.server.*;
import shared.ClientInterface;

public class ClientInterfaceImpl implements ClientInterface {

    private static final long serialVersionUID = 1L;
    private String name;

    public ClientInterfaceImpl() {
        super();
    }

    public ClientInterfaceImpl(String name) {
        super();
        this.name = name;
    }

    @Override
    public void setName(String str) {
        System.out.println("nome alterado");
        this.name = str;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
