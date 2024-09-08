package Modelo;

import Modelo.Cursos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-08T07:46:46")
@StaticMetamodel(Estudiantes.class)
public class Estudiantes_ { 

    public static volatile SingularAttribute<Estudiantes, Integer> estudiante_id;
    public static volatile SingularAttribute<Estudiantes, String> estudiante_nombre;
    public static volatile ListAttribute<Estudiantes, Cursos> curso_asignado;

}