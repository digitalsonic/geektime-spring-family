package geektime.spring.springbucks.waiter.service;

import geektime.spring.springbucks.waiter.model.Coffee;
import geektime.spring.springbucks.waiter.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee saveCoffee(String name, Money price) {
        return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public Coffee getCoffee(Long id) {
        return coffeeRepository.getOne(id);
    }

    public Coffee getCoffee(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }
}
