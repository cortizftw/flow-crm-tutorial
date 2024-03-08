package com.example.application.views.list;

import com.example.application.data.Company;
import com.example.application.data.Status;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import java.util.List;

public class ContactForm extends FormLayout {
    TextField firstName=new TextField("First Name");
    TextField lastName=new TextField("Last Name");
    EmailField email=new EmailField("Email");

    ComboBox<Status> status=new ComboBox<>("Status");
    ComboBox<Company> company=new ComboBox<>("Company");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button cancel=new Button("Cancel");

    public ContactForm(List<Company> com,List<Status> stat)
    {
        addClassName("contact-form");
        company.setItems(com);
        company.setItemLabelGenerator(Company::getName);
        status.setItems(stat);
        status.setItemLabelGenerator(Status::getName);
        add(firstName,lastName,email,company,status,createButton());

    }

    private HorizontalLayout createButton()
    {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save,delete,cancel);
    }
}
