package com.onepiecejoker.service.impl;

import com.onepiecejoker.entity.User;
import com.onepiecejoker.mapper.UserMapper;
import com.onepiecejoker.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2024-03-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
