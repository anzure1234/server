package ams.service.impl;

import ams.model.entity.Order;
import ams.model.entity.User;
import ams.repository.OrderRepository;
import ams.repository.UserRepository;
import ams.service.OrderService;
import ams.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
}
