package view;

import view.components.Input;

import java.util.Scanner;

public abstract class AbstractMenu {
    private final String[] choices;
    private final Scanner scan;

    public AbstractMenu(String[] options) {
        this.choices = options;
        this.scan = Input.getInstance();
    }

    public void execute() {
        for (String choice : choices) {
            System.out.println(choice);
        }
        System.out.print("Escolha uma opção: ");
        Integer choice = scan.nextInt();
        scan.nextLine();
        if (validateChoice(choice)) {
            executeChoice(choice);
        } else {
            System.out.println("Opção inválida");
            execute();
        }
    }

    protected abstract Boolean validateChoice(Integer choice);

    protected abstract void executeChoice(Integer choice);
}