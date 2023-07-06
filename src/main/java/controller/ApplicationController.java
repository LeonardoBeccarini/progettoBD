package controller;

import model.domain.Credentials;

public class ApplicationController implements Controller{
    Credentials cred;

    @Override
    public void start() {
       // while(true){
         //   try{
                LoginController loginController = new LoginController();
                loginController.start();
                cred = loginController.getCred();

                if(cred.getRole() == null) {
                    throw new RuntimeException("Invalid credentials");
                }

                switch(cred.getRole()) {
                    case MANAGER -> new ManagerController().start();
                    case OPERATORE -> new OperatoreController().start();
                    case MAGAZZINIERE -> new MagazziniereController().start();
                    default -> throw new RuntimeException("Invalid credentials");
                }
            /*}catch (Exception e){
                System.out.println(e.toString());
            }

        }*/

    }
}
