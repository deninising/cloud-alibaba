package com.dennis.springcloud.payment.service.impl;

import com.dennis.springcloud.payment.entity.PaymentEntity;
import com.dennis.springcloud.payment.mapper.PaymentMapper;
import com.dennis.springcloud.payment.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng.liao
 * @since 2020-06-21
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, PaymentEntity> implements PaymentService {

}
