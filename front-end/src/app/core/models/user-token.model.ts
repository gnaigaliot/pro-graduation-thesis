export class UserPermission {
  grantedDomain: string;
  operationCode: string;
  resourceCode: string;
  defaultDomain: number;
}

export class UserMenu {
  name: string;
  code: string;
  url: string;
  reourceKey: string;
  sortOrder: string;
  sysMenuId: string;
}

export class UserToken {
  access_token: string;
  email: string;
  userCode: string;
  expires_in: number;
  employeeName: string;
  userName: string;
  phoneNumber: string;
  userId: number;
  loginTime: number;
  tokenExpiresIn: number;
  roleId: number;
  role: string;
  roleName: string;
  userPermissionList: UserPermission[];
  lstRoleCode: string[];
  // userMenuList: UserMenu[];
  userInfo: any;
  employeeImgUrl: string;
  employeeId: number;
}
