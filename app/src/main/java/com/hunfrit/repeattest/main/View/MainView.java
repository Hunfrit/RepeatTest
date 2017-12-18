package com.hunfrit.repeattest.main.View;

/**
 * Created by Artem Shapovalov on 18.12.2017.
 */

public interface MainView {

    void resultForTodayIsSuccessful(float resultForToday, String date);

    void showError(boolean check, int index);

}
