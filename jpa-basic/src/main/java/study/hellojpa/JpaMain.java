package study.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager는 트랜잭션 단위마다 하나씩 만들어야함
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 실제 코드가 들어가는 곳
        try {
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            // 내부적으로 데이터베이스 커넥션을 물고 동장해서 사용다하면 닫아줘야함
            em.close();
        }
        emf.close();
    }
}

