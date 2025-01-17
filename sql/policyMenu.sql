-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补贴政策', '2014', '1', 'policy', 'zhunong/policy/index', 1, 0, 'C', '0', '0', 'zhunong:policy:list', '#', 'admin', sysdate(), '', null, '补贴政策菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补贴政策查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:policy:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补贴政策新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:policy:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补贴政策修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:policy:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补贴政策删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:policy:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补贴政策导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:policy:export',       '#', 'admin', sysdate(), '', null, '');