-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识专家', '2014', '1', 'expert', 'zhunong/expert/index', 1, 0, 'C', '0', '0', 'zhunong:expert:list', '#', 'admin', sysdate(), '', null, '知识专家菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识专家查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:expert:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识专家新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:expert:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识专家修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:expert:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识专家删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:expert:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('知识专家导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:expert:export',       '#', 'admin', sysdate(), '', null, '');