# CalendarViewPage
my first library
我使用了XML的方式来实现month的item，这样我希望可以方便使用者进行自定义布局，而不需要去看代码来进行配置信息。
接着我会继续开放一些属性，可以轻松的更改相关样式。

//必须实现CalendarDataControl接口

FunctionConfig functionConfig = new FunctionConfig.Builder()
                //.setCLICK_DATE_STYLE()
                .setCLICK_OUTSIDE_DATE_THING(true)
                //.setOPEN_SELECT_RANGE()
                //.setSELECT_RANGE_DATE_STYLE()
                .setSHOW_CHINA_DATE(false)
                .setSHOW_OUTSIDE_DATE(true)
                .setWEEKEND_GRAY(true)
                .build();

MyCalendarView m_calendar_view = (MyCalendarView) findViewById(R.id.m_calendar_view);
//functionConfig 要在 setControl 之前
MyCalendarView m_calendar_view.setFunctionConfig(functionConfig);
MyCalendarView m_calendar_view.setControl(this);

这是我的第一个库，会有很多的不足的地方，希望会被提出来，我会进行改进，如果有什么需求也可以联系我，我会进行增加，希望会对你有帮助。
功能会继续增加。。
随后我会继续写一些其他的库，希望有所提高。


QQ:1349961751
