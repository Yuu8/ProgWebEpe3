package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Cursos;
import Modelo.Estudiantes;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EstudiantesJpaController implements Serializable {

    public EstudiantesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EstudiantesJpaController() {
        emf = Persistence.createEntityManagerFactory("Epe3PU");
    }

    public void create(Estudiantes estudiantes) {
        if (estudiantes.getCurso_asignado() == null) {
            estudiantes.setCurso_asignado(new ArrayList<Cursos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cursos> attachedCurso_asignado = new ArrayList<Cursos>();
            for (Cursos curso_asignadoCursosToAttach : estudiantes.getCurso_asignado()) {
                curso_asignadoCursosToAttach = em.getReference(curso_asignadoCursosToAttach.getClass(), curso_asignadoCursosToAttach.getCurso_id());
                attachedCurso_asignado.add(curso_asignadoCursosToAttach);
            }
            estudiantes.setCurso_asignado(attachedCurso_asignado);
            em.persist(estudiantes);
            for (Cursos curso_asignadoCursos : estudiantes.getCurso_asignado()) {
                curso_asignadoCursos.getEstudiante_asignado().add(estudiantes);
                curso_asignadoCursos = em.merge(curso_asignadoCursos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiantes estudiantes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiantes persistentEstudiantes = em.find(Estudiantes.class, estudiantes.getEstudiante_id());
            List<Cursos> curso_asignadoOld = persistentEstudiantes.getCurso_asignado();
            List<Cursos> curso_asignadoNew = estudiantes.getCurso_asignado();
            List<Cursos> attachedCurso_asignadoNew = new ArrayList<Cursos>();
            for (Cursos curso_asignadoNewCursosToAttach : curso_asignadoNew) {
                curso_asignadoNewCursosToAttach = em.getReference(curso_asignadoNewCursosToAttach.getClass(), curso_asignadoNewCursosToAttach.getCurso_id());
                attachedCurso_asignadoNew.add(curso_asignadoNewCursosToAttach);
            }
            curso_asignadoNew = attachedCurso_asignadoNew;
            estudiantes.setCurso_asignado(curso_asignadoNew);
            estudiantes = em.merge(estudiantes);
            for (Cursos curso_asignadoOldCursos : curso_asignadoOld) {
                if (!curso_asignadoNew.contains(curso_asignadoOldCursos)) {
                    curso_asignadoOldCursos.getEstudiante_asignado().remove(estudiantes);
                    curso_asignadoOldCursos = em.merge(curso_asignadoOldCursos);
                }
            }
            for (Cursos curso_asignadoNewCursos : curso_asignadoNew) {
                if (!curso_asignadoOld.contains(curso_asignadoNewCursos)) {
                    curso_asignadoNewCursos.getEstudiante_asignado().add(estudiantes);
                    curso_asignadoNewCursos = em.merge(curso_asignadoNewCursos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estudiantes.getEstudiante_id();
                if (findEstudiantes(id) == null) {
                    throw new NonexistentEntityException("The estudiantes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiantes estudiantes;
            try {
                estudiantes = em.getReference(Estudiantes.class, id);
                estudiantes.getEstudiante_id();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiantes with id " + id + " no longer exists.", enfe);
            }
            List<Cursos> curso_asignado = estudiantes.getCurso_asignado();
            for (Cursos curso_asignadoCursos : curso_asignado) {
                curso_asignadoCursos.getEstudiante_asignado().remove(estudiantes);
                curso_asignadoCursos = em.merge(curso_asignadoCursos);
            }
            em.remove(estudiantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiantes> findEstudiantesEntities() {
        return findEstudiantesEntities(true, -1, -1);
    }

    public List<Estudiantes> findEstudiantesEntities(int maxResults, int firstResult) {
        return findEstudiantesEntities(false, maxResults, firstResult);
    }

    private List<Estudiantes> findEstudiantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiantes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estudiantes findEstudiantes(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudiantesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiantes> rt = cq.from(Estudiantes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
