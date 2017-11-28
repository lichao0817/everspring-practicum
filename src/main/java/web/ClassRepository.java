package web;

import domain.Class;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassRepository extends MongoRepository<Class, String> {}
