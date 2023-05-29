package es.taw.sampletaw.controller;


import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.entity.CuentaEntity;
import es.taw.sampletaw.entity.CuentaclienteEntity;
import es.taw.sampletaw.entity.RolclienteEntity;
import es.taw.sampletaw.entity.TipocomisionEntity;
import es.taw.sampletaw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class controlador {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    CuentaClienteRepository cuentaClienteRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    RolclienteRepository rolclienteRepository;
    @Autowired
    TipocomisionRepository tipocomisionRepository;

    @GetMapping("/")
    public String doListar(Model model) {
        List<CuentaEntity> listaCuentas = this.cuentaRepository.findAll();
        model.addAttribute("listaCuentas", listaCuentas);
        List<TipocomisionEntity> comisiones = this.tipocomisionRepository.findAll();
        model.addAttribute("tipoComision ", comisiones);
        Filtro filtro = new Filtro();
        model.addAttribute("Filtro",filtro);
        return "index";
    }

    @GetMapping("/cuenta")
    public String doMostrarDatosCuenta(Model model, @RequestParam("id") Integer id) {


        CuentaEntity cuenta = this.cuentaRepository.findById(id).orElse(null);
        model.addAttribute("cuenta", cuenta);


        return "datos";
    }


    @GetMapping("/cambiar")
    public String doCambiar(Model model, @RequestParam("id") Integer id, @RequestParam("cliente") Integer cliente) {

        CuentaEntity cuenta = this.cuentaRepository.findById(id).orElse(null);
        model.addAttribute("cuenta", cuenta);
        List<CuentaclienteEntity> clientes = cuenta.getCuentaclientesByCuentaid();
        System.out.println(cliente);

        for (CuentaclienteEntity c : clientes) {
            System.out.println(c.getCuentaclienteid());
            if (c.getClienteByClienteid().getClienteid() == cliente) {
                System.out.println("0------------------------------------------------------------------");
                System.out.println(c.getClienteByClienteid().getNombrecompleto() + c.getRolclienteByRolid().getDescripcion());
                System.out.println("0------------------------------------------------------------------");

                if (c.getRolclienteByRolid().getRolid() == 1) {
                    System.out.println(1);
                    RolclienteEntity rol = new RolclienteEntity();
                    rol.setRolid(2);
                    System.out.println(rol.getDescripcion());
                    c.setRolclienteByRolid(rol);
                    //c.getRolclienteByRolid().setRolid(2);

                    cuentaClienteRepository.save(c);
                    System.out.println("nuevo rol: " + c.getRolclienteByRolid().getDescripcion());
                } else if (c.getRolclienteByRolid().getRolid() == 2) {
                    System.out.println(2);
                    RolclienteEntity rol = new RolclienteEntity();
                    rol.setRolid(1);
                    System.out.println(rol.getDescripcion());
                    c.setRolclienteByRolid(rol);

                    //c.getRolclienteByRolid().setRolid(1);

                    cuentaClienteRepository.save(c);
                    System.out.println("nuevo rol: " + c.getRolclienteByRolid().getDescripcion());

                }
            }
        }
        return doMostrarDatosCuenta(model, id);
    }

    @GetMapping("/borrar")
    public String doBorrar(Model model, @RequestParam("id") Integer id, @RequestParam("cliente") Integer cliente) {


        CuentaEntity cuenta = this.cuentaRepository.findById(id).orElse(null);
        if (cuenta.getCuentaclientesByCuentaid().size() > 1) {
            this.cuentaClienteRepository.delete(this.cuentaClienteRepository.getById(cliente));
        }
        model.addAttribute("cuenta", this.cuentaRepository.findById(id).orElse(null));

        return doMostrarDatosCuenta(model, id);

    }
    @GetMapping("/filtro")
    public String doFiltrar(Model model, @ModelAttribute("filtro")String filtro){


        return doListar(model);
    }
}
