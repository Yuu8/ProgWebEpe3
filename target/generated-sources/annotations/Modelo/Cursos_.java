package Modelo;

import Modelo.Estudiantes;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-08T07:46:46")
@StaticMetamodel(Cursos.class)
public class Cursos_ { 

    public static volatile SingularAttribute<Cursos, Integer> curso_id;
    public static volatile SingularAttribute<Cursos, String> curso_nombre;
    public static volatile ListAttribute<Cursos, Estudiantes> estudiante_asignado;

}