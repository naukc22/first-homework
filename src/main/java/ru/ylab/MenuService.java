package ru.ylab;

public class MenuService {

    public void showNotAuthMenu() {
        System.out.println("Выберите :");
        System.out.println("1 - Зарегистрироваться");
        System.out.println("2 - Войти");
        System.out.println("3 - Закрыть");
    }
    public void showAuthMenu() {
        System.out.println("Выберите :");
        System.out.println("1 - Снять");
        System.out.println("2 - Пополнить");
        System.out.println("3 - Баланс");
        System.out.println("4 - История операция");
        System.out.println("5 - Выйти");
    }
}
