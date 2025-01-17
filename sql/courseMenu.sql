-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识课程', '2014', '1', 'course', 'zhunong/course/index', 1, 0, 'C', '0', '0', 'zhunong:course:list', '#', 'admin', sysdate(), '', null, '知识课程菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识课程查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:course:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识课程新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:course:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识课程修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:course:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识课程删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:course:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识课程导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:course:export',       '#', 'admin', sysdate(), '', null, '');