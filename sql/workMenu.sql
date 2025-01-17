-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('农事服务', '2014', '1', 'work', 'zhunong/work/index', 1, 0, 'C', '0', '0', 'zhunong:work:list', '#', 'admin', sysdate(), '', null, '农事服务菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('农事服务查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:work:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('农事服务新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:work:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('农事服务修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:work:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('农事服务删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:work:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('农事服务导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'zhunong:work:export',       '#', 'admin', sysdate(), '', null, '');