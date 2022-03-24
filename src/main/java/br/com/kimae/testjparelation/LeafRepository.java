package br.com.kimae.testjparelation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafRepository extends JpaRepository<Leaf, Long> {
}
