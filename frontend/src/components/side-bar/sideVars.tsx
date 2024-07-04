type SideBarEntry={
    text:string,
    link:string
};
type SideBarMenu =Record<string, SideBarEntry[]>
type SideVars= Record<string,SideBarMenu>
export const SideVars= {
    'student':{
    '/personal': [
        {
        text:'Личные данные',
        link:'/personal/profile'
        },
        {
        text:'Уведомления',
        link:'personal/notifications'
        },
        {
        text:'Сертификаты',
        link:'personal/certificates'
        },
        {
        text:'Баланс',
        link: 'personal/balance'
        },
        {
        text:'История платежей',
        link:'personal/payment'
        },
        {
        text:'Учеба',
        link:'personal/lessons'
        },
      ],
       '/': [],
    }
} as SideVars