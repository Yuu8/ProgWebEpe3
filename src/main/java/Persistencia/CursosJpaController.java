package Persistencia;

import Modelo.Cursos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Estudiantes;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CursosJpaController implements Serializable {

    public CursosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CursosJpaController() {
        emf = Persistence.createEntityManagerFactory("Epe3PU");
    }

    public void create(Cursos cursos) {
        if (cursos.getEstudiante_asignado() == null) {
            cursos.setEstudiante_asignado(new ArrayList<Estudiantes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiantes> attachedEstudiante_asignado = new ArrayList<Estudiantes>();
            for (Estudiantes estudiante_asignadoEstudiantesToAttach : cursos.getEstudiante_asignado()) {
                estudiante_asignadoEstudiantesToAttach = em.getReference(estudiante_asignadoEstudiantesToAttach.getClass(), estudiante_asignadoEstudiantesToAttach.getEstudiante_id());
                attachedEstudiante_asignado.add(estudiante_asignadoEstudiantesToAttach);
            }
            cursos.setEstudiante_asignado(attachedEstudiante_asignado);
            em.persist(cursos);
            for (Estudiantes estudiante_asignadoEstudiantes : cursos.getEstudiante_asignado()) {
                estudiante_asignadoEstudiantes.getCurso_asignado().add(cursos);
                estudiante_asignadoEstudiantes = em.merge(estudiante_asignadoEstudiantes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cursos cursos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos persistentCursos = em.find(Cursos.class, cursos.getCurso_id());
            List<Estudiantes> estudiante_asignadoOld = persistentCursos.getEstudiante_asignado();
            List<Estudiantes> estudiante_asignadoNew = cursos.getEstudiante_asignado();
            List<Estudiantes> attachedEstudiante_asignadoNew = new ArrayList<Estudiantes>();
            for (Estudiantes estudiante_asignadoNewEstudiantesToAttach : estudiante_asignadoNew) {
                estudiante_asignadoNewEstudiantesToAttach = em.getReference(estudiante_asignadoNewEstudiantesToAttach.getClass(), estudiante_asignadoNewEstudiantesToAttach.getEstudiante_id());
                attachedEstudiante_asignadoNew.add(estudiante_asignadoNewEstudiantesToAttach);
            }
            estudiante_asignadoNew = attachedEstudiante_asignadoNew;
            cursos.setEstudiante_asignado(estudiante_asignadoNew);
            cursos = em.merge(cursos);
            for (Estudiantes estudiante_asignadoOldEstudiantes : estudiante_asignadoOld) {
                if (!estudiante_asignadoNew.contains(estudiante_asignadoOldEstudiantes)) {
                    estudiante_asignadoOldEstudiantes.getCurso_asignado().remove(cursos);
                    estudiante_asignadoOldEstudiantes = em.merge(estudiante_asignadoOldEstudiantes);
                }
            }
            for (Estudiantes estudiante_asignadoNewEstudiantes : estudiante_asignadoNew) {
                if (!estudiante_asignadoOld.contains(estudiante_asignadoNewEstudiantes)) {
                    estudiante_asignadoNewEstudiantes.getCurso_asignado().add(cursos);
                    estudiante_asignadoNewEstudiantes = em.merge(estudiante_asignadoNewEstudiantes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cursos.getCurso_id();
                if (findCursos(id) == null) {
                    throw new NonexistentEntityException("The cursos with id " + id + " no longer exists.");
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
            Cursos cursos;
            try {
                cursos = em.getReference(Cursos.class, id);
                cursos.getCurso_id();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursos with id " + id + " no longer exists.", enfe);
            }
            List<Estudiantes> estudiante_asignado = cursos.getEstudiante_asignado();
            for (Estudiantes estudiante_asignadoEstudiantes : estudiante_asignado) {
                estudiante_asignadoEstudiantes.getCurso_asignado().remove(cursos);
                estudiante_asignadoEstudiantes = em.merge(estudiante_asignadoEstudiantes);
            }
            em.remove(cursos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cursos> findCursosEntities() {
        return findCursosEntities(true, -1, -1);
    }

    public List<Cursos> findCursosEntities(int maxResults, int firstResult) {
        return findCursosEntities(false, maxResults, firstResult);
    }

    private List<Cursos> findCursosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cursos.class));
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

    public Cursos findCursos(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cursos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cursos> rt = cq.from(Cursos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
