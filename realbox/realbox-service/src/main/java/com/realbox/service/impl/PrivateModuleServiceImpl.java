/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.service.impl;

import com.realbox.service.PrivateModuleService;
import org.springframework.stereotype.Service;

/**
 * @author MJJ
 * @create Id: PrivateModuleServiceImpl.java v 0.1 2017年12月06日 上午12:56 MJJ Exp $
 **/
@Service("privateModuleService")
public class PrivateModuleServiceImpl extends BaseService implements PrivateModuleService {

//    @Autowired
//    private PrivateModuleRepository privateModuleRepository;
//
//    @Autowired
//    private RolePrivilegeRepository rolePrivilegeRepository;
//
//    /**
//     * 查询角色私有权限
//     *
//     * @param roleId 角色ID
//     * @return
//     */
//    @Override
//    public RespEntity<Map<String, Object>> queryPrivate(String roleId) {
//        if (StringUtils.isEmpty(roleId)) {
//            return error(RealBox.USER002);
//        }
//
//        // 查询私有模块
//        List<PrivateModule> modules = privateModuleRepository.findByRoleId(roleId);
//        List<String> ids = new ArrayList<String>();
//        for (PrivateModule module : modules) {
//            ids.add(module.getPrivilId());
//        }
//
//        // 查询私有模块权限
//        List<RolePrivilege> privileges = rolePrivilegeRepository.findByIdIn(ids);
//
//        // 返回
//        Map<String, Object> map = Maps.create().add("privileges", privileges).get();
//        return success(map);
//    }
//
//    /**
//     * 新增角色私有权限
//     *
//     * @param modules 角色私有权限对象(List)
//     * @return
//     */
//    @Override
//    public RespEntity<String> createPrivate(List<PrivateModule> modules) {
//        for (PrivateModule module : modules) {
//            if (StringUtils.isEmpty(module.getRoleId())) {
//                return errors(RealBox.USER002);
//            }
//            if (StringUtils.isEmpty(module.getModuleId())) {
//                return errors(RealBox.MODULE000);
//            }
//            if (StringUtils.isEmpty(module.getName())) {
//                return errors(RealBox.MODULE001);
//            }
//        }
//
//        // 删除
//        privateModuleRepository.deleteByRoleId(modules.get(0).getRoleId());
//
//        // 新增
//        privateModuleRepository.save(modules);
//        return success();
//    }
//
//    /**
//     * 编辑角色私有权限
//     *
//     * @param modules 角色私有权限对象(List)
//     * @return
//     */
//    @Override
//    public RespEntity<String> updatePrivate(List<PrivateModule> modules) {
//        for (PrivateModule module : modules) {
//            if (StringUtils.isEmpty(module.getId())) {
//                return errors(RealBox.PRIVIL003);
//            }
//            if (StringUtils.isEmpty(module.getRoleId())) {
//                return errors(RealBox.USER002);
//            }
//            if (StringUtils.isEmpty(module.getPrivilId())) {
//                return errors(RealBox.PRIVIL000);
//            }
//            if (StringUtils.isEmpty(module.getModuleId())) {
//                return errors(RealBox.PRIVIL001);
//            }
//            if (StringUtils.isEmpty(module.getName())) {
//                return errors(RealBox.PRIVIL002);
//            }
//        }
//
//        // 编辑
//        privateModuleRepository.save(modules);
//        return success();
//    }

}
