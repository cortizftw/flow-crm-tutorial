package com.example.application.views.list;


import com.example.application.data.Contact;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.Component;
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
    ContactForm form;
    CrmService service;

    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        ConfigureGrid();
        configureForm();
        add(getToolBar(), getComponent());
        updateList();
    }
    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
    public void ConfigureGrid() {
        //Configure grid
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
    public void configureForm()
    {
        form = new ContactForm(service.findAllCompanies(), service.findAllStatuses());
        form.setWidth("25%");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        Button button = new Button("Add Contact");

        var toolbar = new HorizontalLayout(filterText, button);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private Component getComponent() {
        HorizontalLayout comp = new HorizontalLayout(grid, form);
        comp.setFlexGrow(2, grid);
        comp.setFlexGrow(1, form);
        comp.addClassNames("component");
        comp.setSizeFull();
        return comp;
    }
}
