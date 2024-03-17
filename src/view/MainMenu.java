package view;

import view.client.NewClientView;
import view.vehicle.NewVehicleView;
import view.vehicle.SearchVehicleView;

public class MainMenu extends AbstractMenu{
    public MainMenu(){
        super(new String[]{
            "1 - Cadastrar cliente",
            "2 - Cadastrar veículo",
            "3 - Alugar veículo",
            "4 - Devolver veículo",
            "5 - Editar cliente",
            "6 - Editar veículo",
            "7 - Buscar veículo",
            "8 - Encerrar sessão"
        });
    }

    @Override
    protected Boolean validateChoice(Integer choice){
        return choice >= 1 && choice <= 8;
    }

    @Override
    protected void executeChoice(Integer choice){
        switch (choice){
            case 1 -> new NewClientView().execute();
            case 2 -> new NewVehicleView().execute();
            case 3 -> System.out.println("wip...");
            case 4 -> System.out.println("wip...");
            case 5 -> System.out.println("wip...");
            case 6 -> System.out.println("wip...");
            case 7 -> new SearchVehicleView().execute();
            case 8 -> System.exit(1);
        }
        execute();
    }
}
