package com.example.application.views.list;


import com.example.application.data.Contact;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;



@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout {

    //Create layout
    Grid<Contact> grid = new Grid<>(Contact.class);
    //Create object
    TextField filterText = new TextField();

    public ListView() {
        addClassName("list-view");
        setSizeFull();
        ConfigureGrid();
        add(getToolBar(), grid);
    }

    public void ConfigureGrid() {
        //Configure grid
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button button = new Button();

        var toolbar = new HorizontalLayout(filterText, button);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
